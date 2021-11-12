
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.action.ViewActions.click;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.CustomViewMatcher;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int FAVORITES_ITEMS_COUNT = 1;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void A_myNeighboursFavoritesList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(CustomViewMatcher.withIndex(ViewMatchers.withId(R.id.list_neighbours), 1))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void B_myNeighboursFavoritesList_addNeighbour() {
        onView(CustomViewMatcher.withIndex(ViewMatchers.withId(R.id.list_neighbours), 1))
                .check(withItemCount(FAVORITES_ITEMS_COUNT));

        onView(CustomViewMatcher.withIndex(ViewMatchers.withId(R.id.list_neighbours), 0))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(ViewMatchers.withId(R.id.add_to_favorites)).perform(click());

        onView(ViewMatchers.withId(R.id.go_to_list)).perform(click());

        onView(CustomViewMatcher.withIndex(ViewMatchers.withId(R.id.list_neighbours), 1))
                .check(withItemCount(FAVORITES_ITEMS_COUNT + 1));
    }

    @Test
    public void C_myNeighboursFavoritesList_removeNeighbour() {
        onView(CustomViewMatcher.withIndex(ViewMatchers.withId(R.id.list_neighbours), 1))
                .check(withItemCount(FAVORITES_ITEMS_COUNT + 1));

        onView(CustomViewMatcher.withIndex(ViewMatchers.withId(R.id.list_neighbours), 0))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(ViewMatchers.withId(R.id.add_to_favorites)).perform(click());

        onView(ViewMatchers.withId(R.id.go_to_list)).perform(click());

        onView(CustomViewMatcher.withIndex(ViewMatchers.withId(R.id.list_neighbours), 1))
                .check(withItemCount(FAVORITES_ITEMS_COUNT));
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void D_myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(CustomViewMatcher.withIndex(ViewMatchers.withId(R.id.list_neighbours), 0))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void E_myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(CustomViewMatcher.withIndex(ViewMatchers.withId(R.id.list_neighbours), 0))
                .check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(CustomViewMatcher.withIndex(ViewMatchers.withId(R.id.list_neighbours), 0))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(CustomViewMatcher.withIndex(ViewMatchers.withId(R.id.list_neighbours), 0))
                .check(withItemCount(ITEMS_COUNT-1));
    }
}