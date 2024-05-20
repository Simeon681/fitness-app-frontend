package com.example.fitnessapp1.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp1.R
import com.example.fitnessapp1.resource.response.MealResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NormalSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    onSearchButtonClick: () -> Unit
) {
    val items = remember {
        mutableStateListOf(
            null as String?,
        ).apply {
            remove(null)
        }
    }

    DockedSearchBar(
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        query = query,
        placeholder = {
            Text(text = stringResource(id = R.string.search))
        },
        onQueryChange = {
            onQueryChange(it)
        },
        onSearch = {
            if (!items.contains(query)) {
                items.add(0, query)
            }

            onActiveChange(!active)
            onSearchButtonClick()
        },
        active = active,
        onActiveChange = {
            onActiveChange(it)
        },
        trailingIcon = {
            if (active) {
                IconButton(onClick = {
                    if (query.isEmpty()) {
                        onActiveChange(false)
                    } else {
                        onQueryChange("")
                    }
                }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                }
            }
        }
    ) {
        items.forEach {
            Row(modifier = Modifier.padding(8.dp),
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.padding(end = 10.dp),
                    imageVector = Icons.Default.History,
                    contentDescription = null
                )

                Text(modifier = Modifier.weight(1f, fill = true), text = it ?: "")

                IconButton(
                    onClick = {
                        items.remove(it)
                    }
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }
            }
        }
    }
}

@Composable
fun MealItems(
    active: Boolean,
    meals: List<MealResponse>?,
    onMealIdChange: (String) -> Unit,
    onClick: (Any?) -> Unit
) {
    if (meals != null) {
        if (!active && meals.isNotEmpty()) {
            meals.forEach {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Column(
                            modifier = Modifier.weight(1f, fill = true)
                        ) {
                            Text(
                                text = it.name,
                                fontSize = 20.sp,
                                fontWeight = Bold,
                            )
                            Text(
                                text = "${it.calories} kcal, " +
                                        "${it.protein} g protein, " +
                                        "${it.carbs} g carbs, " +
                                        "${it.fat} g fat",
                                fontSize = 12.sp
                            )
                        }

                        SmallFloatingActionButton(
                            shape = CircleShape,
                            onClick = {
                                onMealIdChange(it.id)
                                onClick(it.id)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}