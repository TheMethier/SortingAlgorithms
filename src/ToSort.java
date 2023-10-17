import java.lang.reflect.Array;
import java.util.*;

public class ToSort {
    public void QuickSort(ArrayList<Movie> tab, int l, int r)
    {
        if (l < r )
        {
            int i = partation(tab, l, r);
            QuickSort(tab, l, i - 1);
            QuickSort(tab, i + 1, r);
        }
    }
    public int partation(ArrayList<Movie> tab,int l, int r)
    {
        int i=l;
        int j=r+1;
        Random random= new Random();
        int pivot=random.nextInt(l,r);
        do {
            do
            {
                i++;
            }
            while(tab.get(i).rating<tab.get(pivot).rating);
            do
            {
                j--;
            }
            while (tab.get(j).rating>tab.get(pivot).rating);
            Collections.swap(tab,i,j);
        }
        while (i<j);
        Collections.swap(tab,i,j);
        Collections.swap(tab,l,j);
        return j;
    }


    public ArrayList<Movie> MergeSort(ArrayList<Movie> tab)
    {
        if(tab.size()==1)
        {
            return tab;
        }
        if (tab.size()>1)
        {
            ArrayList<Movie> tab1 = new ArrayList<>();
            ArrayList<Movie> tab2 = new ArrayList<>();
            int center =tab.size()/2;
            for(int i=0;i<center;i++)
            {
                tab1.add(tab.get(i));
            }
            for(int j=center;j<tab.size();j++)
            {
                tab2.add(tab.get(j));
            }
            tab.clear();
            tab1=MergeSort(tab1);
            tab2=MergeSort(tab2);
            Merge(tab, tab1, tab2);
        }
        return tab;
    }

    public void merge(ArrayList<Movie> tab,ArrayList<Movie> tab1, ArrayList<Movie> tab2)
    {
        while (!(tab1.isEmpty()) && !(tab2.isEmpty()))
        {
            if (tab1.get(0).rating < tab2.get(0).rating)
            {
                tab.add(tab1.remove(0));
            }
            else
            {
                tab.add(tab2.remove(0));
            }
        }
        while (!(tab1.isEmpty()))
        {
            tab.add(tab1.remove(0));
        }
        while (!(tab2.isEmpty()))
        {
            tab.add(tab2.remove(0));
        }
    }
    public void Merge(ArrayList<Movie> tab,ArrayList<Movie> tab1, ArrayList<Movie> tab2)
    {
        int i=0;
        int j=0;
        int k=0;
        int p =tab1.size();
        int q=tab2.size();
        while ((i<p)&&(j<q))
        {
            if(tab1.get(i).rating<=tab2.get(j).rating)
            {
                tab.add(k,tab1.get(i));
                i++;
            }
            else
            {
                tab.add(tab2.get(j));
                j++;
            }
            k++;
        }
        if(i==p)
        {
            for(int o=j;o<tab2.size();o++)
            {
                tab.add(tab2.get(o));
            }

        }
        else
        {
            for(int u=i;u<tab1.size();u++)
            {
                tab.add(tab2.get(u));
            }
        }
    }
    
    public ArrayList<Movie> BucketSort(ArrayList<Movie> tab,int n)
    {
        ArrayList<Movie>[] table=new ArrayList[n+1];
        for(int i=0;i<n+1;i++) table[i]=new ArrayList<Movie>();
        for(Movie y :tab)
        {
            Movie T=y;
            int k=(int)T.rating;
            table[k].add(T);
        }
        tab.clear();
        for(int i=0;i<n+1;i++)
        {
            for(int t=0;t<table[i].size();t++)
            {
                tab.add(table[i].get(t));
            }
        }
        return tab;
    }
}
