/*
 * Copyright 2020 ARUNWORLDLOGIN@GMAIL.COM. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.simplecreations.skippertools

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.simplecreations.skipperlibrary.receylerview.utils.GenericRecyclerViewAdapter
import com.simplecreations.skippertools.databinding.EmptyStateLayoutBinding
import com.simplecreations.skippertools.databinding.ListItemLayoutBinding
import com.simplecreations.skippertools.models.TestData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Dummy data
        val data = arrayListOf(TestData("One"), TestData("Two"), TestData("Three"))
        val emptyStateData = TestData("Empty List")

        //View holder binder for Recycler View list item
        val vieHolderBinder = object : GenericRecyclerViewAdapter.ViewBinder {
            override fun bind(data: Any, binding: ViewDataBinding) {
                (binding as ListItemLayoutBinding).testData = data as TestData
            }
        }

        //View holder binder for empty state
        val emptyStateViewHolderBinder = object : GenericRecyclerViewAdapter.ViewBinder {
            override fun bind(data: Any, binding: ViewDataBinding) {
                (binding as EmptyStateLayoutBinding).testData = data as TestData
            }
        }

        //Setting Recyclerview adapter
        val recycleViewAdapter =
            GenericRecyclerViewAdapter(
                this,
                R.layout.list_item_layout,
                vieHolderBinder,
                data,
                R.layout.empty_state_layout,
                emptyStateViewHolderBinder,
                emptyStateData
            )
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv.adapter = recycleViewAdapter
    }
}
