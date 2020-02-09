package utilities;

import models.Circle;

import java.util.Comparator;

public class SortCirclesComparator implements Comparator<Circle>
{
    @Override
    public int compare(Circle o1, Circle o2)
    {
        if(o1.getWidth() < o2.getWidth())
        {
            return 1;
        }
        return 0;
    }

}
