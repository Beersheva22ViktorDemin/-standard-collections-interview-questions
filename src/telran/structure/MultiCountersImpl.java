package telran.structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MultiCountersImpl implements MultiCounters {
	HashMap<Object, Integer> map = new HashMap<Object, Integer>();

	@Override
	public Integer addItem(Object item) {
		Integer counter = map.get(item);
		if (counter == null) {
			counter = 1;
		} else {
			counter++;
		}
		map.put(item, counter);
		return counter;
	}

	@Override
	public Integer getValue(Object item) {
		return map.get(item);
	}

	@Override
	public boolean remove(Object item) {
		return map.remove(item) != null ? true : false;
	}

	@Override
	public Set<Object> getMaxItems() {
		Integer[] max = {-1};
		map.forEach((item, count) -> {
			if (count > max[0]) {
				max[0] = count;
			}
		});
		Set<Object> set = new HashSet<>();
		map.entrySet().stream().filter(n -> n.getValue() == max[0]).forEach(set::add);
		return set;
	}

}
