package parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Tracer {
	
	public void processArmadaLog(String log) throws Exception {
		File file = new File(log);
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		try {
			Set<String> traceIds = new HashSet<>();
			String str;
			while ((str = br.readLine()) != null) {
				if (str.contains("X-B3-TraceId")) {
					StringTokenizer strTok = new StringTokenizer(str,":");
					strTok.nextToken();
					String st = strTok.nextToken().split("\"")[1].split("\"")[0];
					//System.out.println(st.trim());
					traceIds.add(str.trim());
				}
			}
			
			System.out.println("Number of trace-ids:"+traceIds.size());
			Iterator<String> traceIdsIter = traceIds.iterator();
			while (traceIdsIter.hasNext()) {
				System.out.println(traceIdsIter.next());
			}
		} finally {
			if (br != null) {
				br.close();
			}
		}
		
	}
	
	public static void main(String args[]) {
		Tracer tracer = new Tracer();
		try {
			tracer.processArmadaLog("/Users/sameer.khan/Documents/tracer/traces.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}