import java.util.ArrayList;

/**
 * Created by zajan on 01.02.2017.
 */
public class MatchAllFilter implements Filter
{
    private ArrayList<Filter> filters;

    public MatchAllFilter()
    {
        filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter filter)
    {
        filters.add(filter);
    }

    @Override
    public boolean satisfies(QuakeEntry qe)
    {
        boolean result = false;
        for (Filter element : filters)
        {
            if (element.satisfies(qe))
                result = true;
            else
                return false;
        }

        return result;
    }

    @Override
    public String getName()
    {
        String result = " ";
        for (Filter element: filters)
        {
            result = result + element.getName() + " ";
        }

        return result;
    }
}
