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

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import com.commonsware.cwac.merge.MergeAdapter;

public class MergeAdapterDemo extends ListActivity {
	private static String[] items={"lorem", "ipsum", "dolor",
																	"sit", "amet", "consectetuer",
																	"adipiscing", "elit", "morbi",
																	"vel", "ligula", "vitae",
																	"arcu", "aliquet", "mollis",
																	"etiam", "vel", "erat",
																	"placerat", "ante",
																	"porttitor", "sodales",
																	"pellentesque", "augue",
																	"purus"};
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		
		MergeAdapter adapter=new MergeAdapter();
		
		adapter.addAdapter(buildFirstList());
		adapter.addView(buildButton());
		adapter.addAdapter(buildSecondList());

		setListAdapter(adapter);
	}
	
	private ListAdapter buildFirstList() {
		return(new ArrayAdapter<String>(this,
																		android.R.layout.simple_list_item_1,
																		items));
	}
	
	private View buildButton() {
//		Button result=new Button(this);
		TextView result=new TextView(this);
		
		result.setText("Hello, world!");
		
		return(result);
	}
	
	private ListAdapter buildSecondList() {
		ArrayList<String> list=new ArrayList<String>(Arrays.asList(items));
		
		Collections.shuffle(list);

		return(new ArrayAdapter<String>(this,
																		android.R.layout.simple_list_item_1,
																		list));
	}
}
