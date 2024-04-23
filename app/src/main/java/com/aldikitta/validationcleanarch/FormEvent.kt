package com.aldikitta.validationcleanarch

interface FormEvent {
    data class NameOnChanged(val name: String) : FormEvent
    object Submit : FormEvent
}