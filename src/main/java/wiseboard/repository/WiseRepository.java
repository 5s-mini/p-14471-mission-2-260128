package wiseboard.repository;

import wiseboard.domain.WiseQuote;

public class WiseRepository {

    private WiseQuote[] quotes;
    private Integer size;
    private Integer nextId;

    public WiseRepository() {
        this.quotes = new WiseQuote[10];
        this.size = 0;
        this.nextId = 1;
    }

    public WiseQuote Save(String author, String content) {
        EnsureCapacity();

        Integer id = nextId;
        WiseQuote wiseQuote = new WiseQuote(id, author, content);

        quotes[size] = wiseQuote;
        size++;
        nextId++;

        return wiseQuote;
    }

    public WiseQuote FindById(Integer id) {
        int i = 0;

        while (i < size) {
            WiseQuote quote = quotes[i];

            if (quote.id().equals(id)) {
                return quote;
            }

            i++;
        }

        return null;
    }

    public boolean DeleteById(Integer id) {
        Integer index = FindIndexById(id);

        if (index == null) {
            return false;
        }

        ShiftLeftFrom(index);

        size--;
        quotes[size] = null;

        return true;
    }

    public boolean ReplaceById(Integer id, String author, String content) {
        Integer index = FindIndexById(id);

        if (index == null) {
            return false;
        }

        WiseQuote replaceQuote = new WiseQuote(id, author, content);
        quotes[index] = replaceQuote;

        return true;
    }

    public WiseQuote[] FindAllDesc() {
        WiseQuote[] copy = CopyOfSize();
        SortDescById(copy);
        return copy;
    }

    private void EnsureCapacity() {
        Integer capacity = quotes.length;

        if (size < capacity) {
            return;
        }

        int newCapacity = capacity * 2;
        WiseQuote[] newQuotes = new WiseQuote[newCapacity];

        int i = 0;

        while (i < size) {
            newQuotes[i] = quotes[i];
            i++;
        }

        quotes = newQuotes;
    }

    private WiseQuote[] CopyOfSize() {
        WiseQuote[] copy = new WiseQuote[size];
        int i = 0;

        while (i < size) {
            copy[i] = quotes[i];
            i++;
        }

        return copy;
    }

    private Integer FindIndexById(Integer id) {
        Integer i = 0;

        while (i < size) {
            WiseQuote quote = quotes[i];

            if (quote.id().equals(id)) {
                return i;
            }

            i++;
        }

        return null;
    }

    private void ShiftLeftFrom(Integer index) {
        Integer i = index;

        while (i < size - 1) {
            quotes[i] = quotes[i + 1];
            i++;
        }
    }

    private void SortDescById(WiseQuote[] copy) {
        int n = copy.length;
        int i = 0;

        while (i < n) {
            int j = i + 1;

            while (j < n) {
                WiseQuote quoteA = copy[i];
                WiseQuote quoteB = copy[j];

                if (quoteA.id() < quoteB.id()) {
                    WiseQuote temp = copy[i];
                    copy[i] = copy[j];
                    copy[j] = temp;
                }

                j++;
            }

            i++;
        }
    }
}