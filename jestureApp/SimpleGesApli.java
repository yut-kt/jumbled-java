import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JFrame;


public class SimpleGesApli {
    ArrayList <Pose> poseDefList = new ArrayList<Pose>();
    ArrayList <Datum> datumList = new ArrayList<Datum>();

    public static void main(String[] args) {

        //======================================================
        //ポーズの定義: ポーズ名，point1のジョイントID，point2のジョイントID，単位ベクトルのx成分，y成分，z成分
        //＜具体例＞
        //ポーズ名："LeftArmStraight"
        //point1のジョイントID：4
        //point2のジョイントID：5
        //単位ベクトルのx成分：-1
        //単位ベクトルのy成分：0
        //単位ベクトルのz成分：0
        Pose p1 = new Pose ("LeftArmStraight", 4, 5, -1, 0, 0);

        //p2とp3を自分で定義する
        Pose p2 = new Pose ("RightArmStraight", 8, 9, 1, 0, 0);
        Pose p3 = new Pose ("right", 9, 10, 0, 1, 0);
        //======================================================

        //テストモード=0，kinectを接続=1
        boolean kinect = false;
        if (Integer.parseInt(args[0])==1){
            kinect=true;
        } else {
            kinect=false;
        }

        SimpleGesApli sga = new SimpleGesApli();
        sga.poseDefList.add(p1);
        sga.poseDefList.add(p2);
        sga.poseDefList.add(p3);

        //GUIのスタート
        PictureChanger pc = new PictureChanger ("PictureChanger");
        pc.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        pc.setSize(800, 600);
        pc.setVisible(true);

        try {
            BufferedReader br;
            if (kinect){ //for kinect
                br = new BufferedReader(new InputStreamReader(System.in)); //標準出力を読み込む
            } else {  //for test
                br = new BufferedReader(new FileReader("sampleData.out"));
            }

            String st = new String();
            while ((st = br.readLine()) != null) {
                //System.out.println("Kinect:" + st);
                if (st.startsWith("Tracked")||st.startsWith("Inferred")) {
                    String[] dataArray = st.split(",");
                    //System.out.println(dataArray[0]+" "+dataArray[1]+" "+dataArray[2]+" "+dataArray[3]);
                    Datum data = processData(dataArray);
                    //1回分(25ポイント）の計測データを1つのArrayListに格納
                    if (data.ID==0) {
                        sga.datumList = new ArrayList<Datum>();
                        sga.datumList.add(data);
                    } else if (data.ID==24){
                        sga.datumList.add(data);
                        //類似度のチェック
                        String recResult = sga.gestureRecognition(sga.datumList);
                        System.out.println(recResult);
                        if (recResult!=null){
                            sga.executeClick(pc, recResult);
                            if (!kinect)
                                Thread.sleep(33);
                        }
                    } else {
                        sga.datumList.add(data);
                    }
                }
            }
            //br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String gestureRecognition (ArrayList<Datum> dlist){
        String value = null;
        boolean startFlag=false;
        boolean endFlag = false;
        Datum startObj = new Datum();
        Datum endObj = new Datum();
        boolean result = false;
        for (Pose p: poseDefList) {
            try{
                Thread.sleep(10);
                } catch (Exception e){
                }
            for (Datum data: dlist) {
                if (data.ID==p.startID) {
                    startFlag=true;
                    startObj=data;
                } else if (data.ID==p.endID){
                    endFlag=true;
                    endObj=data;
                }
                if (startFlag==true && endFlag==true){
                    Datum normalized = createNormalizedVector(startObj, endObj);
                    result = calSimilarity(normalized, p.direction_x, p.direction_y, p.direction_z);
                    if (result==true) {
                        value= p.ID;
                    }
                    startFlag=false;
                    endFlag=false;
                    break;
                }
            }
        }
        return value;
    }

    Datum createNormalizedVector (Datum start, Datum end) {
        double xval = end.x - start.x;
        double yval = end.y - start.y;
        double zval = end.z - start.z;
        Datum nomVector = new Datum (-1, null, xval, yval, zval);
        return nomVector;
    }


    boolean calSimilarity(Datum target, int dir_x, int dir_y, int dir_z){
        double cosTheta =0.0;
        double norm = calNorm(target);
        double ip = calInnerProduct (target, dir_x, dir_y, dir_z);
        cosTheta = ip/norm*1; //単位ベクトルの方は大きさ1だから
        System.out.println(ip+" "+norm+" "+cosTheta);
        if (cosTheta>0.906) {
            System.out.println("**RECOGNIZED!!**RECOGNIZED!!**RECOGNIZED!!**RECOGNIZED!!**RECOGNIZED!!**");
            return true;
        } else {
            return false;
        }
    }

    double calNorm (Datum jd) {
        return Math.sqrt((jd.x*jd.x)+(jd.y*jd.y)+(jd.z*jd.z));
    }

    double calInnerProduct (Datum jd, int dir_x, int dir_y, int dir_z){
        return (jd.x*dir_x)+(jd.y*dir_y)+(jd.z*dir_z);
    }


    void executeClick(PictureChanger pc, String posename){
        //======================================================
        //対応させたいボタンを定義
        //doClickの引数はクリックしている時間長(ms)
        if (posename.equals("LeftArmStraight")) {
            pc.pict3.doClick(100);
        }  else if  (posename.equals("RightArmStraight")) {
            pc.pict1.doClick(100);
        } else if (posename.equals("right")) {
            pc.pict2.doClick(100);
        }
        //======================================================
    }

    //データを1行読んでDatumクラスのオブジェクトを生成
    static Datum processData(String[] dataArray){
        String st = dataArray[0];
        String xst = dataArray[1];
        String yst = dataArray[2];
        String zst = dataArray[3];
        String[] sta = st.split(" ");
        String [] staa = sta[1].split("=");
        int id = Integer.parseInt(staa[1]);
        String[] xta = xst.split("= ");
        String[] yta = yst.split("= ");
        String[] zta = zst.split("= ");
        Datum dataObj = new Datum(id, sta[0],Double.parseDouble(xta[1]),
                Double.parseDouble(yta[1]), Double.parseDouble(zta[1]));
        return dataObj;
    }

}

class Datum {
    int ID;
    String status;
    double x;
    double y;
    double z;

    Datum () {

    }

    Datum(int i, String s, double xvalue, double yvalue, double zvalue){
        ID=i;
        status=s;
        x=xvalue;
        y=yvalue;
        z=zvalue;
    }

    void printData() {
        System.out.println(this.ID+","+this.status+","+this.x+","+this.y+","+this.z);
    }
}

class Pose {
    String ID; //ポーズ名
    int startID; //p1のジョイント
    int endID; //p2のジョイント
    int direction_x; //単位ベクトルのx成分
    int direction_y; //単位ベクトルのy成分
    int direction_z; //単位ベクトルのz成分

    Pose (String id, int si, int ei, int x, int y, int z){
        this.ID=new String(id);
        this.startID=si;
        this.endID=ei;
        this.direction_x=x;
        this.direction_y=y;
        this.direction_z=z;
    }

}

