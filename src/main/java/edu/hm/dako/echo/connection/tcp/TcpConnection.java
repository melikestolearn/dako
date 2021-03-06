package edu.hm.dako.echo.connection.tcp;

import edu.hm.dako.echo.connection.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;

public class TcpConnection implements Connection {

    private static Log log = LogFactory.getLog(TcpConnection.class);

    // Groesse des Empfangspuffers einer TCP-Verbindung in Byte
    private static final int RECEIVE_BUFFER_SIZE = 300000;

    // Ein- und Ausgabestrom der Verbindung
    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    
    // Verwendetes TCP-Socket
    private final Socket socket;

    public TcpConnection(Socket socket) {
        this.socket = socket;

        log.info(Thread.currentThread().getName() + ": Verbindung mit neuem Client aufgebaut, Remote-TCP-Port " +
                socket.getPort());

        try {
        	//TODO Studienarbeit: Objektstreams fuer die Ein- und Ausgabe erzeugen
         
        	

            log.debug("Standardgroesse des Empfangspuffers der Verbindung: " + socket.getReceiveBufferSize() +
                    " Byte");
            socket.setReceiveBufferSize(RECEIVE_BUFFER_SIZE);
            log.debug("Eingestellte Groesse des Empfangspuffers der Verbindung: " + socket.getReceiveBufferSize() +
                    " Byte");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Serializable receive() throws Exception {
    	//TODO Studienarbeit: Nachricht aus dem Eingabestrom lesen und als Returnwert zurueckgeben
   
    
    }

    @Override
    public void send(Serializable message) throws Exception {
    	//TODO Studienarbeit: Nachricht in den Ausgabestrom schreiben
    
    	
    }

    @Override
    public void close() throws IOException {
    	//TODO Studienarbeit: Ausgabestrom leeren (flush) und Verbindung schliessen 
    
    	
    }
}
