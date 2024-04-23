package com.aldikitta.validationcleanarch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aldikitta.validationcleanarch.ui.theme.ValidationcleanarchTheme

@Composable
fun Form(modifier: Modifier = Modifier) {

    val viewModel = viewModel<FormViewModel>()
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    val email = remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = Unit, block = {
        val json = context.assets.open("form_errors.json")
            .bufferedReader()
            .use { it.readText() }

        viewModel.jsonMock(json)
    })

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Form Validation",
            modifier = modifier
        )

        OutlinedTextField(
            value = state.name,
            onValueChange = {
                viewModel.onEvent(FormEvent.NameOnChanged(it))
            },
            label = {
                Text(text = "Name")
            },
            placeholder = {
                Text(text = "Name")
            },
            supportingText = {
                Text(text = state.nameErrorMessage?.asString() ?: "")
            },
            isError = (state.nameErrorMessage?.asString() ?: "").isNotBlank()
        )

        OutlinedTextField(
            value = email.value,
            onValueChange = {
                email.value = it
            },
            label = {
                Text(text = "Email")
            },
            placeholder = {
                Text(text = "Email")
            }
        )

        Button(onClick = {
            viewModel.onEvent(FormEvent.Submit)
        }, enabled = state.isEnableButtonSubmit) {
            Text(text = "Submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ValidationcleanarchTheme {
        Form()
    }
}