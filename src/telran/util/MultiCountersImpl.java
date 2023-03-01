package telran.util;

import java.util.*;

public class MultiCountersImpl implements MultiCounters {
 private HashMap<Object, Integer> items=new HashMap<>();
 private TreeMap<Integer, HashSet<Object>> counters = new TreeMap<>();
	@Override
	public Integer addItem(Object item) {
		Integer count = items.getOrDefault(item, 0);
		moveItemCounters(count, item);
		items.put(item, ++count);
		return count;
	}

	private void moveItemCounters(Integer count, Object item) {
		if (count != 0) {
			removeCountersItem(count, item);
		}

		addCountersItem(count + 1, item);

	}

	private void addCountersItem(int counter, Object item) {
		HashSet<Object> set = counters.get(counter);
		if (set == null) {
			set = new HashSet<>();
			counters.put(counter, set);
		}
		set.add(item);


	}

	@Override
	public Integer getValue(Object item) {

		return items.get(item);
	}

	@Override
	public boolean remove(Object item) {
		boolean res = false;
		Integer count = items.remove(item);
		if (count != null) {
			res = true;
			removeCountersItem(count, item);
		}
		return res;
	}

	private void removeCountersItem(Integer count, Object item) {
		HashSet<Object> set = counters.get(count);
		set.remove(item);
		if(set.isEmpty()) {
			counters.remove(count);
		}

	}

	@Override
	public Set<Object> getMaxItems() {
		var lastEntry = counters.lastEntry();
		return lastEntry != null ? lastEntry.getValue() : Collections.emptySet();
	}

}