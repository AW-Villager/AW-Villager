package awvillager.net;

import javax.swing.JTextArea;

import org.aiwolf.common.data.Player;
import org.aiwolf.common.data.Role;

import awvillager.ui.GUIVillagerClientStarter;

public class NetUtil {

    public static void startClient(Class<? extends Player> playerClass,Role role){

        try{
            if(playerClass == null){
                return;
            }
            final Player player = playerClass.newInstance();
            final int port = getPort();
            final String host = getHost();

            Runnable r = new Runnable() {

                @Override
                public void run() {

                    AWVTcpipClient client = new AWVTcpipClient(host, port, role);

                    if(client.connect(player)){

                        System.out.println("Player connected to server:"+player);

                        GUIVillagerClientStarter.frame.addMessage("接続 : " + playerClass);

                        JTextArea area =
                                GUIVillagerClientStarter.frame.logPanel.addAgent(playerClass);

                        client.setJTextArea(area);

                        /*for(JTextField field: agentFieldList){
                            if(field.getText().isEmpty()){
                                StringBuffer buf = new StringBuffer();
                                buf.append(host);
                                buf.append(":");
                                buf.append(port);
                                buf.append(" ");
                                buf.append(String.format("%s(%s)", player.getName(), role));
                                field.setText(buf.toString());
                                break;
                            }
                        }*/
                    }
                    else{

                    }
                }
            };

            Thread t = new Thread(r);
            t.start();

        }catch(NumberFormatException e){
//          log("players must be number");
        }catch(IllegalArgumentException e){
//          log(e.getMessage());
        } catch (InstantiationException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }

    public static int getPort(){
        return 10000;
    }

    public static String getHost(){
        return "localhost";
    }

}
