# ValuePicker
An Android library that provides a simple and customizable ValuePicker.

![](https://img.shields.io/badge/API-21%2B-orange.svg?style=flat)
[![Platform](https://img.shields.io/badge/platform-Android-green.svg)](http://developer.android.com/index.html)
[![Download](https://img.shields.io/maven-central/v/com.paulrybitskyi.valuepicker/valuepicker.svg?label=Download)](https://search.maven.org/search?q=com.paulrybitskyi.valuepicker)
[![Build](https://github.com/mars885/value-picker/workflows/Build/badge.svg?branch=master)](https://github.com/mars885/value-picker/actions)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ValuePicker-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/8212)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Contents

* [Demo](#demo-youtube)
* [Installation](#installation)
* [Usage](#usage)
* [Advanced Usage](#advanced-usage)
* [License](#license)

## Demo (YouTube)

<a href="https://www.youtube.com/watch?v=qzoZh3aYlcY">
<img src="/media/demo_thumbnail.png" width="200" height="422"/>
</a>

## Installation

1. Make sure that you've added the `mavenCentral()` repository to your top-level `build.gradle` file.

````groovy
buildscript {
    //...
    repositories {
        //...
        mavenCentral()
    }
    //...
}
````

2. Add the library dependency to your module-level `build.gradle` file.

````groovy
dependencies {
    //...
    implementation "com.paulrybitskyi.valuepicker:valuepicker:1.0.3"
    //...
}
````

## Usage
Basic usage of the ValuePickerView involves two steps - declaring a widget inside the XML file of your choice and configuring it in one of the Kotlin/Java classes.

Let's see how we can do that by following the steps listed above:

1. Declaring a widget inside the XML file.

    <details><summary><b>XML (click to expand)</b></summary>
    <p>

    ````xml
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/colorPrimary">

        <!-- Other widgets here -->

        <com.paulrybitskyi.valuepicker.ValuePickerView
            android:id="@+id/valuePickerView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:vpv_areDividersEnabled="true"
            app:vpv_isInfiniteScrollEnabled="true"
            app:vpv_maxVisibleItems="5"
            app:vpv_textColor="@color/colorAccent"
            app:vpv_dividerColor="@color/colorAccent"
            app:vpv_flingSpeedFactor="0.3"
            app:vpv_textSize="@dimen/date_picker_text_size"
            app:vpv_textTypeface="@font/ubuntu_mono_bold"
            app:vpv_divider="@drawable/custom_divider"
            app:vpv_orientation="vertical"/>

    </androidx.constraintlayout.widget.ConstraintLayout>
    ````
    </p></details>

2. Configuring the widget in one of the Kotlin/Java classes.

    <details><summary><b>Kotlin (click to expand)</b></summary>
    <p>

    ````kotlin
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //...

        with(valuePickerView) {
            onItemSelectedListener = ValuePickerView.OnItemSelectedListener { item ->
                // Do something with item
            }

            val pickerItems = getPickerItems()

            items = pickerItems
            setSelectedItem(pickerItems[2])
        }
    }


    private fun getPickerItems(): List<Item> {
        return mutableListOf<Item>().apply {
            for(number in 1..100) {
                add(
                    PickerItem(
                        id = number,
                        title = number.toString()
                    )
                )
            }
        }
    }
    ````

    </p></details>

    <details><summary><b>Java (click to expand)</b></summary>
    <p>

    ````java
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ValuePickerView valuePickerView = view.findViewById(R.id.valuePickerView);
        valuePickerView.setOnItemSelectedListener((item) -> {
            // Do something with item
        });

        final ArrayList<Item> pickerItems = getPickerItems();

        valuePickerView.setItems(getPickerItems());
        valuePickerView.setSelectedItem(pickerItems.get(2));
    }


    private ArrayList<Item> getPickerItems() {
        final ArrayList<Item> pickerItems = new ArrayList<>(100);

        for(int i = 1; i <= 100; i++) {
            pickerItems.add(
                new PickerItem(
                    i,
                    String.valueOf(i)
                )
            );
        }

        return pickerItems;
    }
    ````

    </p></details>

## Advanced Usage

See the [Sample app](https://github.com/mars885/value-picker/tree/master/sample/src/main/java/com/paulrybitskyi/valuepicker/sample).

## License

ValuePicker is licensed under the [Apache 2.0 License](LICENSE).
