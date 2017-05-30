package awvillager.net;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.Socket;

import javax.swing.JTextArea;

import org.aiwolf.common.AIWolfRuntimeException;
import org.aiwolf.common.data.Role;
import org.aiwolf.common.net.DataConverter;
import org.aiwolf.common.net.Packet;
import org.aiwolf.common.net.TcpipClient;

/**
 *  通信を解析するためのTCPクライアント
 *  大会などで使用するのは禁止
 *  */
public class AWVTcpipClient extends TcpipClient{

    JTextArea area;

    public AWVTcpipClient(String host, int port, Role requestRole) {
        super(host, port, requestRole);
    }

    @Override
    public void run() {

        try{
            // サーバと接続されたソケットを利用して処理を行う。

            Socket socket = null;
            try {
                socket = (Socket) getPrivateField(this, "socket");
            } catch (Exception e) {
                e.printStackTrace();
            }

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while((line = br.readLine()) != null){

                //System.out.println(line);

                if(area!=null){

                    String lineIn = line;

                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            area.append(lineIn + "\n");
                            area.setCaretPosition(area.getText().length());
                        }
                    });

                }

                Packet packet = DataConverter.getInstance().toPacket(line);

                Object obj = recieve(packet);
                if(packet.getRequest().hasReturn()){
                    if(obj == null){
                        bw.append("\n");
//                      throw new NoReturnObjectException(player+" "+obj);
                    }
                    else if(obj instanceof String){
                        bw.append(obj+"\n");
                    }
                    else{
//                      System.err.println(packet.getRequest());
                        bw.append(DataConverter.getInstance().convert(obj)+"\n");
                    }
                    bw.flush();
                }
            }
//          System.out.println("Close connection of "+player);
//          br.close();
//          bw.close();
//          socket.close();
//          System.out.println("Finish game");
        }catch(IOException e){
            if(this.isConnecting()){
                try {
                    setPrivateField(this, "isConnecting", Boolean.FALSE);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                //isConnecting = false;
                if(isRunning()){
                    try {
                        setPrivateField(this, "isRunning", Boolean.FALSE);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    //isRunning = false;
                    throw new AIWolfRuntimeException(e);
                }
            }
        }finally{
//          isRunning = false;
        }
    }

    public static Object getPrivateField(Object target_object, String field_name) throws Exception{


        final Field fields[] = Class.forName(TcpipClient.class.getName()).getDeclaredFields();

        Class c = TcpipClient.class;//target_object.getClass();
        Field fld = c.getDeclaredField(field_name);
        fld.setAccessible(true);

        return fld.get(target_object);
    }

    public static void setPrivateField(Object target_object, String field_name, Object value) throws Exception{
        Class c = target_object.getClass();
        Field fld = c.getDeclaredField(field_name);
        fld.setAccessible(true);
        fld.set(target_object, value);
    }

    public void setJTextArea(JTextArea area){
        this.area = area;
    }

}
