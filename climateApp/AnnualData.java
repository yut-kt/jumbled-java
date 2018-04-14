import java.util.ArrayList;

public class AnnualData {
    int year;
    ArrayList <DataObj> monthList = new ArrayList <DataObj> ();

    AnnualData(String data) {
        String[] d_array = data.split(",");
        this.year = Integer.parseInt(d_array[0]);
        if (d_array.length == 13) {
            for (int i = 1; i < d_array.length; i++) {
                DataObj monj = new DataObj(new String(i + "月"), Double.parseDouble(d_array[i]));
                this.monthList.add(monj);
            }
        } else {
            System.out.println("Wrong data length");
        }
    }

    void printAnnualData() {
        System.out.println(year + "年");
        for (DataObj dt : this.monthList) {
            System.out.println(dt.name + ":" + dt.value);
        }
        System.out.println("");
    }
}
