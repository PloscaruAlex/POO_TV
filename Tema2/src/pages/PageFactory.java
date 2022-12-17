package pages;

/**
 * This is the factory that creates each page.
 */
public final class PageFactory {
    private PageFactory() {
    }

    /**
     * This is the main function of the factory, that returns
     * an instance of each page.
     *
     * @param name the name of the page
     * @return the page
     */
    public static Page createPage(final String name) {
        switch (name) {
            case "homepageUnauthenticated":
                return HomepageUnauthenticated.getInstance();
            case "login":
                return LoginPage.getInstance();
            case "register":
                return RegisterPage.getInstance();
            case "homepageAuthenticated":
                return HomepageAuthenticated.getInstance();
            case "logout":
                return LogoutPage.getInstance();
            case "movies":
                return MoviesPage.getInstance();
            case "see details":
                return SeeDetailsPage.getInstance();
            case "upgrades":
                return UpgradesPage.getInstance();
            default:
                break;
        }
        throw new IllegalArgumentException("Not a page.");
    }
}
