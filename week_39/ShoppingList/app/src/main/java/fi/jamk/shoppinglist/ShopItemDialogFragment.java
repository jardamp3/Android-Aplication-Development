package fi.jamk.shoppinglist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by seide on 01.10.2017.
 */

public class ShopItemDialogFragment extends DialogFragment
{
    public interface ShopItemDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String productName, int count, double price);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    ShopItemDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (ShopItemDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement TextEntryDialogListener");
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // create a new AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View dialogView = inflater.inflate(R.layout.shopitem_dialog,
                null);
        builder.setView(dialogView)
                .setTitle("Add item")
                // Add action buttons
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // find a team name
                        EditText productTxt = (EditText) dialogView.findViewById(R.id.productTxt);
                        EditText countTxt = (EditText) dialogView.findViewById(R.id.countTxt);
                        EditText price = (EditText) dialogView.findViewById(R.id.priceTxt);


                        // Send the positive button event back to the host activity
                        mListener.onDialogPositiveClick(ShopItemDialogFragment.this,
                                productTxt.getText().toString(),
                                Integer.parseInt(countTxt.getText().toString()),
                                Double.parseDouble(price.getText().toString()));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the negative button event back to the host activity
                        mListener.onDialogNegativeClick(ShopItemDialogFragment.this);
                    }
                });
        return builder.create();
    }
}
