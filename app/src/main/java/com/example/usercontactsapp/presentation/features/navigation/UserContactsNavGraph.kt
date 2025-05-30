package com.example.usercontactsapp.presentation.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.usercontactsapp.presentation.features.contactadd.ContactAddScreen
import com.example.usercontactsapp.presentation.features.contactinfo.ContactInfoScreen
import com.example.usercontactsapp.presentation.features.contactlist.ContactListScreen
import com.example.usercontactsapp.presentation.features.useredit.UserEditScreen
import com.example.usercontactsapp.presentation.features.userform.UserFormScreen
import com.example.usercontactsapp.presentation.features.userinfo.UserInfoScreen
import androidx.navigation.toRoute

@Composable
fun UserContactsNavGraph(
    navController: NavHostController, startDestination: AppDestination
) {
    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        composable<UserForm> {
            UserFormScreen(
                onSave = {
                    navController.navigate(UserInfo) {
                        popUpTo(UserForm) {
                            inclusive = true
                        }
                    }
                })
        }

        composable<UserInfo> {
            UserInfoScreen(onEdit = {
                navController.navigate(UserEdit)
            }, onOpenContacts = {
                navController.navigate(ContactList)
            })
        }

        composable<UserEdit> {
            UserEditScreen(
                onSave = {
                    navController.navigate(UserInfo) {
                        popUpTo(UserEdit) {
                            inclusive = true
                        }
                    }
                })
        }

        composable<ContactList> {
            ContactListScreen(
                onAddClick = { navController.navigate(ContactAdd) },
                onContactClick = { contact ->
                    navController.navigate(ContactInfo(contact.id))
                })
        }

        composable<ContactAdd> {
            ContactAddScreen(onBack = { navController.popBackStack() })
        }

        composable<ContactInfo> { backStackEntry ->
            val contactId = backStackEntry.toRoute<ContactInfo>().contactId
            ContactInfoScreen(contactId = contactId)
        }
    }
}