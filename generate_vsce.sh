cd vscode
npm list | npm install
vsce package | npm install -g vsce && vsce package

code --install-extension intraj-0.0.5.vsix
code -r
