package utils;

import io.FiltersInput;

public final class FiltersField {
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

    /**
     * Sets the values of the filters field from the input files.
     * @param filters the input for the filters field
     */
    public void setFiltersFromInput(final FiltersInput filters) {
        if (filters == null) {
            return;
        }
        this.sort.setSortFromInput(filters.getSort());
        this.contains.setContainsFromInput(filters.getContains());
    }
}
