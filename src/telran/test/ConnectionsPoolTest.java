package telran.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import telran.util.Connection;
import telran.util.ConnectionsPoolImp;

public class ConnectionsPoolTest {
	@Test
	void connectionsPoolTest() {
		var pool = new ConnectionsPoolImp(3);
		assertTrue(pool.addConnection(new Connection(100, null, 0)));
		assertFalse(pool.addConnection(new Connection(100, null, 0)));
		assertTrue(pool.addConnection(new Connection(200, null, 0)));
		assertTrue(pool.addConnection(new Connection(300, null, 0)));
		
		assertNull(pool.getConnection(0));
		assertNotNull(pool.getConnection(100));
		assertNotNull(pool.getConnection(200));
		assertNotNull(pool.getConnection(300));
		
		assertTrue(pool.addConnection(new Connection(400, null, 0)));
		assertNotNull(pool.getConnection(400));
		assertNull(pool.getConnection(100));
		assertNotNull(pool.getConnection(200));
	}
}
