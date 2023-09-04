package student_records;

import java.io.*;
import java.util.*;

public class StudentRecords {
    private Map<String, Set<Record>> records;
    private Map<String, Map<Integer, Integer>> grades;

    public StudentRecords() {
        this.records = new TreeMap<String, Set<Record>>();
        this.grades = new HashMap<String, Map<Integer, Integer>>();
    }

    public int readRecords(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        int total = 0;
        while (scanner.hasNextLine()) {
            ++total;
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            String code = parts[0];
            String type = parts[1];
            Record record = new Record(code, type);
            for (int i = 2; i < parts.length; i++) {
                int grade = Integer.parseInt(parts[i]);
                Map<Integer, Integer> map = grades.get(type);
                if (map == null) {
                    map = new TreeMap<Integer, Integer>();
                    grades.put(type, map);
                }
                Integer count = map.get(grade);
                if (count == null)
                    count = 0;
                ++count;
                map.put(grade, count);
                record.addGrade(grade);
            }
            Set<Record> list = records.get(type);
            if (list == null) {
                list = new TreeSet<Record>();
                records.put(type, list);
            }
            list.add(record);
        }
        scanner.close();
        return total;
    }

    public void writeTable(OutputStream outputStream) {
        PrintWriter writer = new PrintWriter(outputStream);
        for (String key : records.keySet()) {
            writer.println(key);
            Set<Record> set = records.get(key);
            for (Record record : set) {
                writer.println(record);
            }
        }
        writer.flush();
    }

    public void writeDistribution(OutputStream outputStream) {
        PrintWriter writer = new PrintWriter(outputStream);
        Set<Stat> stats = new TreeSet<Stat>();
        for (Map.Entry<String, Map<Integer, Integer>> entry : grades.entrySet()) {
            stats.add(new Stat(entry.getKey(), entry.getValue().get(10)));
        }
        for (Stat stat : stats) {
            writer.println(stat.getType());
            Map<Integer, Integer> typeStat = grades.get(stat.getType());
            for (Map.Entry<Integer, Integer> entry : typeStat.entrySet()) {
                writer.printf("%2d | ", entry.getKey());
                for (int i = 0; i < entry.getValue(); i += 10) {
                    writer.print("*");
                }
                writer.printf("(%d)", entry.getValue());
                writer.println();
            }
        }
        writer.flush();
    }
}
