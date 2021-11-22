import com.sun.source.doctree.EntityTree;

import javax.print.DocFlavor;
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static class Entity implements Comparable<Entity>{
		int c,a;

		public Entity(int c, int a) {
			this.c = c;
			this.a = a;
		}

		@Override
		public int compareTo(Entity o) {
			if(this.c == o.c)
			{
				return a-o.a;
			}
			else
				return c-o.c;
		}
	}
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		List<Entity>[] list = new ArrayList[n+1];
		int []d = new int [n+1];
		Arrays.fill(d,987654321);
		PriorityQueue<Entity> pq = new PriorityQueue<>();
		for(int i=1;i<=n;i++)
			list[i] = new ArrayList<>();
		for(int i=0;i<m;i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new Entity(c,b));
			list[b].add(new Entity(c,a));
		}

		pq.add(new Entity(0,1));
		d[1] = 0;
		while(!pq.isEmpty())
		{
			int cur = pq.peek().a;
			int cur_dis = pq.peek().c;
			pq.poll();
			if(cur_dis > d[cur])
				continue;
			for(int i=0;i<list[cur].size();i++)
			{
				int next = list[cur].get(i).a;
				int next_dis =cur_dis + list[cur].get(i).c;
				if(next_dis < d[next])
				{
					pq.add(new Entity(next_dis,next));
					d[next] = next_dis;
				}
			}
		}
		/*for(int i=1;i<=n;i++)
		{
			System.out.print(d[i]+" ");
		}*/
		System.out.println(d[n]);
	}

}


