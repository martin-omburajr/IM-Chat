
import java.io.DataInputStream ;
import java.io.PrintStream ;
import java.io.BufferedInputStream ;
import java.io.IOException ;
import java.net.Socket ;
import java.net.UnknownHostException ;

public class Client {

	public static void main(String [] args)
	{
		Socket clientSocket = null;
		DataInputStream is = null;
		PrintStream os = null;
		DataInputStream inputLine = null;
		
		/*
		 * Open a socket n port 2222. Open the input and the output streams.
		 */
		
		try
		{
			clientSocket = new Socket("localhost",2222);
			os = new PrintStream(clientSocket.getOutputStream());
			is = new DataInputStream(clientSocket.getInputStream());
			inputLine = new DataInputStream(new BufferedInputStream(System.in));	
		}catch(UnknownHostException e)
		{
			System.err.println("Dont know about the host!");
		}catch(IOException e)
		{
			System.err.println("Couldnt get I/O for the connection to host");
		}
		
		/*
		 * If everything has been initialized then we want ot write some data to the socket
		 * we have opened a connection ot on port 2222.
		 */
		
		if(clientSocket != null && os != null && is != null)
		{
			try
			{
				/*
				 * Keep on reading from/to socket till we receive the "OK" form the server, 
				 * once  we received hat then we break
				 */
				
				System.out.println("The client started. Type any text. To quit it type 'OK'.");
				String responseLine;
				os.println(inputLine.readLine());
				
				while ((responseLine = is.readLine()) != null)
				{
					System.out.println(responseLine);
					if(responseLine.indexOf("Ok") != -1)
					{
						break;
					}
					os.println(inputLine.readLine());
				}
				
				os.close();
				is.close();
				clientSocket.close();
			}catch( UnknownHostException e)
			{
				System.err.println("TRying to connect to unknown host: " + e);
			}catch(IOException e)
			{
				System.err.println("IOException: " + e);
			}
			
		}
		
	}
	
}
