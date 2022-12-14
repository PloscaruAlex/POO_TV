package pages;

public class PageFactory {
    public static Page createPage(String name) {
        switch(name) {
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
        }
        throw new IllegalArgumentException("Not a page.");
    }
}
