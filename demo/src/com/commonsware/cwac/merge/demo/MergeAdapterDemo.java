/***
  Copyright (c) 2008-2009 CommonsWare, LLC
  
  Licensed under the Apache License, Version 2.0 (the "License"); you may
  not use this file except in compliance with the License. You may obtain
  a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

package com.commonsware.cwac.merge.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import com.commonsware.cwac.merge.MergeAdapter;

public class MergeAdapterDemo extends ListActivity {
  private static final String[] items=
      { "lorem", "ipsum", "dolor", "sit", "amet", "consectetuer",
          "adipiscing", "elit", "morbi", "vel", "ligula", "vitae",
          "arcu", "aliquet", "mollis", "etiam", "vel", "erat",
          "placerat", "ante", "porttitor", "sodales", "pellentesque",
          "augue", "purus" };
  private MergeAdapter adapter=null;
  private ArrayAdapter<String> arrayAdapter=null;

  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    setContentView(R.layout.main);

    adapter=new MergeAdapter();
    arrayAdapter=buildFirstList();
    adapter.addAdapter(arrayAdapter);
    adapter.addView(buildButton(), true);
    adapter.addAdapter(buildSecondList());
    adapter.addView(buildLabel());
    adapter.addAdapter(buildSecondList());

    setListAdapter(adapter);

    MergeAdapter spinnerAdapter=new MergeAdapter();

    spinnerAdapter.addAdapter(buildFirstSpinnerList());
    spinnerAdapter.addAdapter(buildSecondSpinnerList());

    ((Spinner)findViewById(R.id.spinner)).setAdapter(spinnerAdapter);
  }

  @Override
  public void onListItemClick(ListView parent, View v, int position,
                              long id) {
    Log.d("MergeAdapterDemo", String.valueOf(position));
  }

  private ArrayAdapter<String> buildFirstList() {
    return(new ArrayAdapter<String>(
                                    this,
                                    android.R.layout.simple_list_item_1,
                                    new ArrayList<String>(
                                                          Arrays
                                                                .asList(items))));
  }

  private View buildButton() {
    Button result=new Button(this);

    result.setText("Add Capitalized Words");
    result.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        for (String item : items) {
          arrayAdapter.add(item.toUpperCase());
        }
      }
    });

    return(result);
  }

  private View buildLabel() {
    TextView result=new TextView(this);

    result.setText("Hello, world!");

    return(result);
  }

  private ListAdapter buildSecondList() {
    ArrayList<String> list=new ArrayList<String>(Arrays.asList(items));

    Collections.shuffle(list);

    return(new ArrayAdapter<String>(
                                    this,
                                    android.R.layout.simple_list_item_1,
                                    list));
  }

  private ArrayAdapter<String> buildFirstSpinnerList() {
    ArrayAdapter<String> result=
        new ArrayAdapter<String>(
                                 this,
                                 android.R.layout.simple_spinner_item,
                                 new ArrayList<String>(
                                                       Arrays
                                                             .asList(items)));

    result
          .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    return(result);
  }

  private ListAdapter buildSecondSpinnerList() {
    ArrayList<String> list=new ArrayList<String>(Arrays.asList(items));

    Collections.shuffle(list);

    ArrayAdapter<String> result=
        new ArrayAdapter<String>(this,
                                 android.R.layout.simple_spinner_item,
                                 list);

    result
          .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    return(result);
  }
}
