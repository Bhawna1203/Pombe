package com.maku.pombe.ui.components.latest

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.placeholder.placeholder
import com.maku.pombe.R
import com.maku.pombe.common.presentation.model.latest.UILatestDrink
import com.maku.pombe.searchfeature.presentation.SearchViewModel
import com.maku.pombe.ui.components.common.CardBottom
import com.maku.pombe.ui.components.common.CardImage
import com.maku.pombe.ui.components.common.CategoryCard
import com.maku.pombe.ui.components.common.Like

@Composable
fun LatestCard(drink: UILatestDrink,
               modifier: Modifier = Modifier,
               favoriteDrink: () -> Unit,
               onItemClick: (String) -> Unit,
               searchViewModel: SearchViewModel
) {
    val colors = MaterialTheme.colors
    val context = LocalContext.current
        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(20.dp),
            backgroundColor = colors.background,
            contentColor = colors.onBackground,
            modifier = modifier.size(300.dp),
        ) {
            ConstraintLayout(
                modifier = modifier.fillMaxSize(),
            ) {
                val (
                    drinkImage, bottomCard, categoryCard, like
                ) = createRefs()

                CardImage(
                    modifier
                        .constrainAs(drinkImage) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .clickable {
                            onItemClick(drink.id)
                            searchViewModel.updateSearchWidgetState(newValue = "NOBAR")
                        },
                    topEnd = 20.dp ,
                    topStart = 20.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp,
                    imageHeight = 220.dp,
                    drink = drink,
                    context = context
                )

                CardBottom(modifier
                    .constrainAs(bottomCard) {
                        top.linkTo(drinkImage.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }, favoriteDrink, drink)

                CategoryCard(modifier = modifier.constrainAs(categoryCard) {
                    start.linkTo(drinkImage.start)
                    top.linkTo(drinkImage.top)
                    width = Dimension.fillToConstraints
                },
                    topStart = 20.dp ,
                    topEnd = 0.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp,
                    drink.category,
                    colors.onPrimary,
                    Color(0xFFFFA500)
                )

                Like(modifier.constrainAs(like) {
                    end.linkTo(drinkImage.end)
                    top.linkTo(bottomCard.top)
                    bottom.linkTo(bottomCard.bottom)
                    width = Dimension.fillToConstraints
                },favoriteDrink)

            }
    }
}

@Composable
fun LatestCardPlaceHolder(
    modifier: Modifier
) {
    Column() {
        Row(
            modifier
                .fillMaxWidth()
                .height(220.dp)
                .placeholder(
                    visible = true,
                    color = Color.Gray,
                    // optional, defaults to RectangleShape
                    shape = RoundedCornerShape(20.dp),
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,)
        { }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier
                .fillMaxWidth()
                .height(50.dp)
                .placeholder(
                visible = true,
            color = Color.Gray,
            // optional, defaults to RectangleShape
            shape = RoundedCornerShape(20.dp),
        ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,)
        { }
    }
}

