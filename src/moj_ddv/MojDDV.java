package moj_ddv;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MojDDV {
    private List<Receipt> receipts;

    public void readRecords(InputStream in) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

        this.receipts = bufferedReader.lines()
                .map(line -> {
                    try {
                        return Receipt.createReceipt(line);
                    } catch (AmountNotAllowedException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public void printTaxReturns(PrintStream out) {
        PrintStream pw = new PrintStream(out);
        receipts.stream()
                        .forEach(item -> pw.println(item.toString()));
        pw.flush();
    }

    public void printStatistics(PrintStream out) {
        PrintWriter pw = new PrintWriter(out);
        DoubleSummaryStatistics summaryStatistics = receipts.stream()
                        .mapToDouble(Receipt::getTaxReturn)
                        .summaryStatistics();

        pw.println(String.format("min:\t%05.03f\nmax:\t%05.03f\nsum:\t%05.03f\ncount:\t%-5d\navg:\t%05.03f",
            summaryStatistics.getMin(),
        summaryStatistics.getMax(),
        summaryStatistics.getSum(),
        summaryStatistics.getCount(),
        summaryStatistics.getAverage()));

        pw.flush();
    }
}
