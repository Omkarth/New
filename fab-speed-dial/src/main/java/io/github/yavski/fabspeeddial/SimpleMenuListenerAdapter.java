package io.github.yavski.fabspeeddial;

import android.view.MenuItem;

/**
 * This adapter class provides empty implementations of the methods from
 * {@link FabSpeedDial.MenuListener}.
 * Created by yavorivanov on 03/01/2016.
 */
public class SimpleMenuListenerAdapter implements FabSpeedDial.MenuListener {
    @Override
    public boolean onPrepareMenu(NavigationMenu navigationMenu) {
        return false;
    }

    @Override
    public boolean onMenuItemSelected(MenuItem menuItem) {
        return false;
    }

    @Override
    public void onMenuClosed() {
    }
}
