package org.jenkinsci.plugins.github.config.GitHubServerConfig

import org.jenkinsci.plugins.github.config.GitHubServerConfig

def f = namespace(lib.FormTagLib);
def c = namespace(lib.CredentialsTagLib)


f.entry(title: _("Manage hooks"), field: "manageHooks") {
    f.checkbox(default: true)
}

f.entry(title: _("Credentials"), field: "credentialsId") {
    c.select()
}

f.optionalBlock(title: _("Custom GitHub API URL"),
        inline: true,
        field: "customApiUrl",
        checked: instance?.customApiUrl) {
    f.entry(title: _("GitHub API URL"), field: "apiUrl") {
        f.textbox(default: GitHubServerConfig.GITHUB_URL)
    }
}

f.advanced() {
    f.entry(title: _("GitHub client cache size (MB)"), field: "clientCacheSize") {
        f.textbox(default: GitHubServerConfig.DEFAULT_CLIENT_CACHE_SIZE_MB)
    }
}

f.block() {
    f.validateButton(
            title: _("Verify credentials"),
            progress: _("Verifying..."),
            method: "verifyCredentials",
            with: "apiUrl,credentialsId,clientCacheSize"
    )
}

