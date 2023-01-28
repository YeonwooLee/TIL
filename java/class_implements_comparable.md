```
package temp;

import java.util.PriorityQueue;

public class Temp {
	static int[][] a= new int[10][10];
	public static void main(String[] args) {
		PriorityQueue<Dog> pq = new PriorityQueue<>();
		pq.offer(new Dog(3));
		pq.offer(new Dog(1));
		pq.offer(new Dog(2));
		pq.offer(new Dog(4));
		pq.offer(new Dog(7));
		pq.offer(new Dog(5));
		pq.offer(new Dog(6));
		pq.offer(new Dog(8));
		
		while(!pq.isEmpty()) {
			System.out.print(pq.poll().weight+" "); //1 2 3 4 5 6 7 8 
		}
		
	}
	
	static class Dog implements Comparable<Dog>{
		int weight;
		public Dog(int weight) {
			this.weight = weight;
		}
		@Override
		public int compareTo(Dog o) {
			return this.weight - o.weight; 
		}
	}

}

//오늘 과제 : 5-2, 6-4
```