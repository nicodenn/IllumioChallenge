public class illumioChallenge{
    public static void main(String[] args)
    {
        firewall newWall = new firewall("text.csv");
        if(firewall.accept_packet("outbound", "udp", "1001", "1.20.1.0"))
        {
            System.out.println("Permission");
        }
        else{
            System.out.println("Denied");
        }
    }

}