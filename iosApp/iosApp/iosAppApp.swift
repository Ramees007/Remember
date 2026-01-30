//
//  iosAppApp.swift
//  iosApp
//
//  Created by Shabnam on 1/25/26.
//

import SwiftUI
import SharedIOSApi

@main
struct iosAppApp: App {
    
    init() {
        IosAppGraphHolder.shared.setup()
    }
    
    var body: some Scene {
        WindowGroup {
            RootView()
        }
    }
}
