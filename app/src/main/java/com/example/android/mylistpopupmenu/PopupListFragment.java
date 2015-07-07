package com.example.android.mylistpopupmenu;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class PopupListFragment extends ListFragment implements View.OnClickListener

{
    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);
        String item = (String) l.getItemAtPosition(position);
        Toast.makeText(getActivity(), "Item Clicked: " + item, Toast.LENGTH_SHORT).show();
    }
/* private OnFragmentInteractionListener mListener; */

    @Override
    public void onClick(View v)
    {
        Toast.makeText(getActivity(), "Clicked: ", Toast.LENGTH_SHORT).show();
        showPopupMenu(v);
    }

    private void showPopupMenu(View v)
    {
        final PopupAdapter adapter = (PopupAdapter) getListAdapter();
        final String item = (String) v.getTag();
        PopupMenu popup = new PopupMenu(getActivity(), v);
        popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        {
            public boolean onMenuItemClick(MenuItem menuitem)
            {
                switch (menuitem.getItemId())
                {
                    case R.id.menu_remove:
                        // Remove the item from the adapter
                        ((PopupAdapter) getListAdapter()).remove(item);
                        return true;
                    case R.id.menu_add:
                        return true;
                }
                return false;
            }
        });
        popup.show();
    }


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PopupListFragment()
    {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0, z = Cheeses.CHEESES.length; i < z; i++)
        {
            items.add(Cheeses.CHEESES[i]);
        }

        // Set the ListAdapter
        setListAdapter(new PopupAdapter(items));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return super.onCreateView(inflater, container, savedInstanceState);
    }



    class PopupAdapter extends ArrayAdapter<String>
    {

        PopupAdapter(ArrayList<String> items)
        {
            super(getActivity(), R.layout.list_item, android.R.id.text1, items);
        }

        @Override
        public void remove(String object)
        {
            super.remove(object);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container)
        {
            // Let ArrayAdapter inflate the layout and set the text
            View view = super.getView(position, convertView, container);

            // BEGIN_INCLUDE(button_popup)
            // Retrieve the popup button from the inflated view
            View popupButton = view.findViewById(R.id.button_popup);

            // Set the item as the button's tag so it can be retrieved Flater
            popupButton.setTag(getItem(position));

            // Set the fragment instance as the OnClickListener
            popupButton.setOnClickListener(PopupListFragment.this);
            // END_INCLUDE(button_popup)

            // Finally return the view to be displayed
            return view;
        }
    }
}
