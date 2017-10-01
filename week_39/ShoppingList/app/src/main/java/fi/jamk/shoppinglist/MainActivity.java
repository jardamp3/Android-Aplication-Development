package fi.jamk.shoppinglist;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity
{
    ListView shoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find list view
        shoppingList = (ListView) findViewById(R.id.shoppingListView);
        // register listView's context menu (to delete row)
        registerForContextMenu(shoppingList);


    }
}
