package pages;

public interface PeoplePageInterface {

    boolean isFriend();

    PeoplePage addFriend();

    boolean isFriendRequestSended();

    PeoplePage removingFriendRequests();

    ToolBar getToolbar();

}
