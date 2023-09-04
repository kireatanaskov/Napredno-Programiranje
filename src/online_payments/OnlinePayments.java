package online_payments;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class OnlinePayments {
    private Map<String, Student> studentMap;

    public OnlinePayments() {
        this.studentMap = new HashMap<>();
    }

    public void readItems(InputStream in) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

        bufferedReader.lines().map(Item::create).forEach(item -> {
            studentMap.putIfAbsent(item.getIndex(), new Student(item.getIndex()));
            studentMap.get(item.getIndex()).addItem(item);
        });
    }

    public void printStudentReport(String index, OutputStream os) {
        PrintWriter pw = new PrintWriter(os);
        Student s = studentMap.get(index);

        if (s == null) {
            pw.println(String.format("Student %s not found!", index));
        } else {
            pw.println(s);
        }
        pw.flush();
    }
}
