package telran.util;

import java.util.*;

public class ConnectionsPoolImp implements ConnectionsPool {
	private int poolSize;
	private Map<Integer, Connection> map = new HashMap<Integer, Connection>();
	private LinkedList<Integer> pool = new LinkedList<Integer>();
	
	public ConnectionsPoolImp(int poolSize) {
		super();
		this.poolSize = poolSize;
	}

	@Override
	public boolean addConnection(Connection connection) {
		boolean result = false;
		Integer id = connection.getId();
		if (!map.containsKey(id)) {
			if (map.size() == poolSize) {
				removeOldConnection();
			}
			map.put(id, connection);
			pool.add(id);
			
			result = true;
		}
		
		return result;
	}

	private void removeOldConnection() {
		Integer id = pool.remove();
		map.remove(id);
	}

	@Override
	public Connection getConnection(int id) {
		Connection result = map.get(id);
		if (result != null) {
			updateCounter(id);
		}
		return result;
	}

	private void updateCounter(int id) {
		pool.removeFirstOccurrence(id);
		pool.add(id);
	}
}
