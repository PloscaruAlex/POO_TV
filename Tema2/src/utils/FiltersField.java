package utils;

import io.FiltersInput;

public class FiltersField {
    private SortField sort = new SortField();
    private ContainsField contains = new ContainsField();

    public SortField getSort() {
        return sort;
    }

    public void setSort(final SortField sort) {
        this.sort = sort;
    }

    public ContainsField getContains() {
        return contains;
    }

    public void setContains(final ContainsField contains) {
        this.contains = contains;
    }

    public void setFiltersFromInput(FiltersInput filters) {
        if (filters == null) {
            return;
        }
        this.sort.setSortFromInput(filters.getSort());
        this.contains.setContainsFromInput(filters.getContains());
    }
}
