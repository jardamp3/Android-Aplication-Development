package fi.jamk.shoppinglist;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity implements ShopItemDialogFragment.ShopItemDialogListener
{
    ListView shoppingList;
    SQLiteDatabase db;
    Cursor cursor;
    double price;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find list view
        shoppingList = (ListView) findViewById(R.id.shoppingListView);
        // register listView's context menu (to delete row)
        registerForContextMenu(shoppingList);

        // get database instance
        db = (new DatabaseOpenHelper(this)).getWritableDatabase();
        // get data with using own made queryData method
        queryData();
    }

    public void addItem(View view) {
        ShopItemDialogFragment eDialog = new ShopItemDialogFragment();
        eDialog.show(getFragmentManager(), "Add product");
    }

    // query data from database
    public void queryData() {
        //cursor = db.rawQuery("SELECT _id, name, score FROM highscores ORDER BY score DESC", null);
        // get data with query

        String[] resultColumns = new String[]{"_id","name","count","price"};
        cursor = db.query("shopping_list",resultColumns,null,null,null,null,"_id DESC",null);

        // add data to adapter
        ListAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.list_item, cursor,
                new String[] {"name", "count", "price"},      // from
                new int[] {R.id.nameCol, R.id.countCol, R.id.priceCol}    // to
                ,0);  // flags

        // show data in listView
        shoppingList.setAdapter(adapter);

        cursor = db.rawQuery("SELECT sum(price*count) as Total FROM shopping_list;",null);



        while (cursor.moveToNext())
        {
            price = cursor.getDouble(0);
        }

        Toast.makeText(this,"Total:" + price, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String productName, int count, double price)
    {
        ContentValues values=new ContentValues(3);
        values.put("name", productName);
        values.put("count", count);
        values.put("price", price);

        db.insert("shopping_list", null, values);
        // get data again
        queryData();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog)
    {

    }
}
