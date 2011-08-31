package com.commonsware.cwac.merge;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;

/**
 * Adapter that merges multiple child adapters into a single
 * contiguous whole to be consumed by a Spinner.
 * 
 * Adapters used as pieces within MergeSpinnerAdapter must have
 * view type IDs monotonically increasing from 0. Ideally,
 * adapters also have distinct ranges for their row ids, as
 * returned by getItemId().
 * 
 * All Adapters used as pieces within MergeSpinnerAdapter
 * must be properly-configured implementations of
 * SpinnerAdapter (e.g., ArrayAdapter, CursorAdapter).
 */
public class MergeSpinnerAdapter extends MergeAdapter {
  /**
   * Stock constructor, simply chaining to the superclass.
   */
  public MergeSpinnerAdapter() {
    super();
  }

  /*
   * Returns the drop-down View for a given position, by
   * iterating over the pieces. Assumes that all pieces are
   * implementations of SpinnerAdapter.
   * 
   * @see android.widget.BaseAdapter#getDropDownView(int,
   * android.view.View, android.view.ViewGroup)
   */
  public View getDropDownView(int position, View convertView,
                              ViewGroup parent) {
    for (ListAdapter piece : pieces) {
      int size=piece.getCount();

      if (position<size) {
        return(((SpinnerAdapter)piece).getDropDownView(position,
                                                       convertView,
                                                       parent));
      }

      position-=size;
    }

    return(null);
  }
}
