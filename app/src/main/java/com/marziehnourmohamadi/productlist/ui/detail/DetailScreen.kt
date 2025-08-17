package com.marziehnourmohamadi.productlist.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.marziehnourmohamadi.productlist.R
import com.marziehnourmohamadi.productlist.domain.model.ProductItemModel
import com.marziehnourmohamadi.productlist.domain.model.RatingModel
import com.marziehnourmohamadi.productlist.ui.theme.OmidPayProductListTheme


@Composable
fun DetailScreen(
    model: ProductItemModel? = null,
    viewModel: DetailViewModel = hiltViewModel()
) {

    Column(modifier = Modifier
        .padding(horizontal = 16.dp)
        .verticalScroll(rememberScrollState())) {

            AsyncImage(
                model = model?.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .aspectRatio(1f)
            )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconWithText(icon = Icons.Rounded.Star, text = model?.rating?.rate.toString())

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = stringResource(R.string.from_comments, model?.rating?.count.toString()),
                style = MaterialTheme.typography.bodySmall,
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = {
                viewModel.toggleBookmark(model!!)
            }) {
                Icon(
                    imageVector = Icons.Rounded.Favorite,
                    contentDescription = "Bookmarked",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Text(
            text = model?.title ?: "",
            style = MaterialTheme.typography.titleMedium,
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Text(
                text = stringResource(R.string.price),
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = model?.price.toString(),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = model?.description ?: "",
            style = MaterialTheme.typography.bodyMedium,
        )


        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.category),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = model?.category ?: "",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 12.dp, vertical = 6.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun IconWithText(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Preview
@Composable
private fun DetailScreenPrev() {
    OmidPayProductListTheme {
        DetailScreen(
            ProductItemModel(
                id = 1, title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                price = 109.95,
                description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                category = "men's clothing",
                image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png",
                rating = RatingModel(rate = 3.9, count = 120),
            )
        )
    }
}