package telran.structure;

import java.util.*;

public class MultiCountersImpl implements MultiCounters {
	HashMap<Object, Integer> map = new HashMap<Object, Integer>();
	ArrayList<Set<Object>> maxList = new ArrayList<>();

	@Override
	public Integer addItem(Object item) {
		Integer counter = map.get(item);
		if (counter == null) {
			counter = 1;
		} else {
			counter++;
		}
		map.put(item, counter);
		
		
		if (counter >= maxList.size()) {
			if (counter > maxList.size()) {
				maxList.add(new HashSet<Object>());
			}
		}
		maxList.get(counter - 1).add(item);
		if (counter > 1) {
			removeFromMax(item, counter - 1);
		}
		
		return counter;
	}

	@Override
	public Integer getValue(Object item) {
		return map.get(item);
	}

	@Override
	public boolean remove(Object item) {
		Integer count = map.remove(item);
		boolean result = count != null ? true : false;
		if (count != null && count == maxList.size()) {
			removeFromMax(item, count);
		}
		
		return result;
	}

	private void removeFromMax(Object item, Integer count) {
		Set<Object> list = maxList.get(count - 1);
		list.remove(item);
		if (list.isEmpty() && count.equals(maxList.size())) {
			maxList.remove(count - 1);
		}
	}
	
	@Override
	public Set<Object> getMaxItems() {
		Set<Object> set = new HashSet<>();
		if (!maxList.isEmpty()) {
			maxList.get(maxList.size() - 1).forEach(set::add);
		}
		return set;
	}

}
