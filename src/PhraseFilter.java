/**
 * Created by zajan on 01.02.2017.
 */
public class PhraseFilter implements Filter
{
    private String where;
    private String phrase;

    public PhraseFilter(String myWhere, String myPhrase)
    {
        where = myWhere;
        phrase = myPhrase;
    }

    @Override
    public boolean satisfies(QuakeEntry qe)
    {
        boolean result = false;

        String title = qe.getInfo();
        if (title.contains(phrase))
        {
            switch (where) {
                case "start":
                    if (title.startsWith(phrase))
                        result = true;
                    break;
                case "end":
                    if (title.endsWith(phrase))
                        result = true;
                    break;
                case "any":
                    result = true;
            }
        }

        return  result;
    }

    @Override
    public String getName()
    {
        return "Phrase";
    }
}
