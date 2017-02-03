import java.util.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

//import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public static void main (String[] args) throws ParserConfigurationException, SAXException, IOException
    {
        EarthQuakeClient2 client = new EarthQuakeClient2();
        client.testMatchAllFilter();

    }

    public void testMatchAllFilter()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);

        //for (QuakeEntry element : list)
        //    System.out.println(element);
        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0, 2));
        maf.addFilter(new DepthFilter(-100000, -10000));
        maf.addFilter(new PhraseFilter("any", "a"));

        ArrayList<QuakeEntry> result = filter(list, maf);

        for (QuakeEntry element : result)
            System.out.println(element);

        System.out.println("Filters used are:" + maf.getName());
    }

    public void testMatchAllFilter2()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);

        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0, 3));
        maf.addFilter(new DistanceFilter((new Location(36.1314, -95.9372)), 10000000));
        maf.addFilter(new PhraseFilter("any", "Ca"));
        ArrayList<QuakeEntry> answer = filter(list, maf);

        for(QuakeEntry element: answer)
            System.out.println(element);

    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            if (f.satisfies(qe)) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public void quakesWithFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f = new MinMagFilter(4.0);
        //Filter f = new MagnitudeFilter(4, 5);
        //Filter fd = new DepthFilter(-35000, -12000);
        Location location = new Location(35.42, 139.43);
        Filter f = new DistanceFilter(location, 10000000);
        Filter fd = new PhraseFilter("end", "Japan");
        ArrayList<QuakeEntry> m7  = filter(list, f);
        ArrayList<QuakeEntry> m8 = filter(m7, fd);
        for (QuakeEntry qe: m8) {
            System.out.println(qe);
        }
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }
    }

}

