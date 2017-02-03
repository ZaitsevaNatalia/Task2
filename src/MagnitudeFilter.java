/**
 * Created by zajan on 01.02.2017.
 */
public class MagnitudeFilter implements Filter
{
    private double minMagnitude;
    private double maxMagnitude;

    public MagnitudeFilter(double min, double max)
    {
        minMagnitude = min;
        maxMagnitude = max;
    }

    @Override
    public boolean satisfies(QuakeEntry qe)
    {
        return (qe.getMagnitude() >= minMagnitude && qe.getMagnitude() <= maxMagnitude);
    }

    @Override
    public String getName()
    {
        return "Magnitude";
    }
}
