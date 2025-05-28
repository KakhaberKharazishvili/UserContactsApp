package com.example.usercontactsapp.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.usercontactsapp.features.contactadd.ContactAddScreen
import com.example.usercontactsapp.features.contactinfo.ContactInfoScreen
import com.example.usercontactsapp.features.contactlist.ContactScreen
import com.example.usercontactsapp.features.useredit.UserEditScreen
import com.example.usercontactsapp.features.userform.UserFormScreen
import com.example.usercontactsapp.features.userinfo.UserInfoScreen

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
            ContactScreen(
                onAddClick = { navController.navigate(ContactAdd) },
                onContactClick = { contact ->
                    navController.navigate(ContactInfo(contact.id))
                })
        }

        composable<ContactAdd> {
            ContactAddScreen(onBack = { navController.popBackStack() })
        }

        composable<ContactInfo> { backStackEntry ->
            val contactId = backStackEntry.arguments?.getInt("contactId") ?: return@composable
            ContactInfoScreen(contactId = contactId)
        }
    }
}