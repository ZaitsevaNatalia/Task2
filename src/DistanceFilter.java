/**
 * Created by zajan on 01.02.2017.
 */
public class DistanceFilter implements Filter
{
    private Location location;
    private double maxDistance;

    public DistanceFilter(Location loc, double dist)
    {
        location = loc;
        maxDistance = dist;
    }

    @Override
    public boolean satisfies(QuakeEntry qe)
    {
        return (location.distanceTo(qe.getLocation()) < maxDistance);
    }

    @Override
    public String getName()
    {
        return "Distance";
    }
}
