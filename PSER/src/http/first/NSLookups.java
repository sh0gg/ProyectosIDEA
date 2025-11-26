package http.first;

import java.net.InetAddress;

public class NSLookups {
    public static void main(String[] args) throws Exception {
        System.out.println(InetAddress.getLocalHost()); //MIMAQUINA/192.168.1.34
        System.out.println(InetAddress.getByName(null)); //localhost/127.0.0.1
        System.out.println(InetAddress.getByName("ieschandomonte.edu.es")); //ieschandomonte.edu.es/82.98.160.22
        System.out.println(InetAddress.getByName("google.es")); //google.es/173.194.41.247
        System.out.println(InetAddress.getByAddress(new byte[]{(byte)192,(byte)168,33,25})); // /192.168.33.25
        InetAddress ips[] = InetAddress.getAllByName("google.es");
        for(InetAddress ip : ips)
            System.out.println(ip); // google.es/173.194.41.248 google.es/173.194.41.247 google.es/173.194.41.255

    }
}
