package edu.hm.dako.echo.client;

import edu.hm.dako.echo.benchmarking.UserInterfaceInputParameters;
import edu.hm.dako.echo.common.SharedClientStatistics;
import edu.hm.dako.echo.connection.ConnectionFactory;
import edu.hm.dako.echo.connection.DecoratingConnectionFactory;
import edu.hm.dako.echo.connection.rmi.RmiClientConnectionFactory;
import edu.hm.dako.echo.connection.tcp.TcpConnectionFactory;
import edu.hm.dako.echo.connection.udp.UdpClientConnectionFactory;

/**
 * Uebernimmt die Konfiguration und Erzeugung bestimmter Client-Typen.
 * Siehe {@link edu.hm.dako.echo.benchmarking.UserInterfaceInputParameters.ImplementationType}
 * Dies beinhaltet die {@link ConnectionFactory}, die Adressen, Ports, Denkzeit etc.
 */
public final class ClientFactory {

    private ClientFactory() {
    }

    public static Runnable getClient(UserInterfaceInputParameters param, int numberOfClient, SharedClientStatistics sharedData) {
        try {
            switch (param.getImplementationType()) {
                case TCPSingleThreaded:
                    return new NewConnectionClient(param.getRemoteServerPort(),
                            param.getRemoteServerAddress(), numberOfClient, param.getMessageLength(),
                            param.getNumberOfMessages(), param.getClientThinkTime(),
                            sharedData, getDecoratedFactory(new TcpConnectionFactory()));
                case TCPMultiThreaded:
                	//TODO Studienarbeit: Konfiguration und Erzeugung des richtigen Clients
                    
                	
                	
                case UDPSingleThreaded:
                	//TODO Studienarbeit: Konfiguration und Erzeugung des richtigen Clients
              
                	
                	
                	
                case UDPMultiThreaded:
                    return new ConnectionReusingClient(param.getRemoteServerPort(),
                            param.getRemoteServerAddress(), numberOfClient, param.getMessageLength(),
                            param.getNumberOfMessages(), param.getClientThinkTime(),
                            sharedData, getDecoratedFactory(new UdpClientConnectionFactory()));
                case RmiMultiThreaded:
                    return new ConnectionReusingClient(param.getRemoteServerPort(),
                            param.getRemoteServerAddress(), numberOfClient, param.getMessageLength(),
                            param.getNumberOfMessages(), param.getClientThinkTime(),
                            sharedData, getDecoratedFactory(new RmiClientConnectionFactory()));
                default:
                    throw new RuntimeException("Unknown type: " + param.getImplementationType());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ConnectionFactory getDecoratedFactory(ConnectionFactory connectionFactory) {
        return new DecoratingConnectionFactory(connectionFactory);
    }
}