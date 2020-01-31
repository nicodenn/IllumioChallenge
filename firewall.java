import java.io.BufferedReader;
import java.io.*;

public class firewall
{
    public static String file = "text.csv";
    public firewall(String fileName)
    {
        file = fileName;
    }
    public static boolean accept_packet(String direction, String protocol, String port, String IP)
    {
        try{
            BufferedReader read = new BufferedReader(new FileReader(file));
            String line;
                while((line = read.readLine())!=null)
                {
                   String[] elements = line.split(",");
                   if(direction.equals(elements[0]))
                   {
                       if(protocol.equals(elements[1]))
                       {  
                            if(elements[2].contains("-"))
                            {
                                String[] portRange = elements[2].split("-");
                                int portLow = Integer.parseInt(portRange[0]);
                                int portHigh = Integer.parseInt(portRange[1]);
                                int newPort = Integer.parseInt(port);
                                if(newPort >= portLow && newPort <= portHigh)
                                {
                                    if(elements[3].contains("-"))
                                    {
                                        String[] IPRange = elements[3].split("-");
                                        if(compareIP(IPRange[0], IPRange[1], IP))
                                        {
                                            return true; 
                                        }
                                    }
                                    else if(IP.equals(elements[3]))
                                    {
                                        return true;
                                    }
                                }
                            }
                            else if(port.equals(elements[2]))
                            {
                                if(elements[3].contains("-"))
                                {
                                    String[] IPRange = elements[3].split("-");
                                    if(compareIP(IPRange[0], IPRange[1], IP))
                                    {
                                        return true; 
                                    }
                                }
                                else if(IP.equals(elements[3]))
                                {
                                    return true;
                                }
                            }
                            
                            
                       }
                       
                   }
                }
    
            }
            catch(IOException e)
            {
                System.out.println("Error");
                System.exit(0);
            }
        
        
        
        return false;
    }
    public static boolean compareIP(String IP1, String IP2, String IPComp)
    {
        String[] firstIP = IP1.split("\\."); 
        String[] secondIP = IP2.split("\\."); 
        String[] comp = IPComp.split("\\.");
        
        for(int i = 0; i < 4; i++)
        {
            int tempFirst = Integer.parseInt(firstIP[i]); 
            int tempSecond = Integer.parseInt(secondIP[i]); 
            int tempComp = Integer.parseInt(comp[i]); 
            if(tempComp < tempFirst || tempComp > tempSecond)
            {
                return false;
            }
        }
        
        return true;
    }
    
}