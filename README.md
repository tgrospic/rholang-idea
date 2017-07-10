# [RHOlang][rho-github] IntelliJ IDEA language plugin

![beta][beta-badge]

This is the early stage of editor support for [RHOlang][rho-github]. :smile:

RHOlang is official language for [RChain][rchain-coop] distributed virtual machine. Is is currently in active development and syntax can slightly change. Current version of plugin follows this version of [RHO grammar][rho-bnf] and available examples.

I tried to make parser more forgiving so it can highlight wider range of correct programs. With more context information it could be much more precise. For now this will not recognize `*my_chan.ref[index]!(my_val)` although I'm not sure whether this is a valid syntax.

I found interesting that many grammar rules have a strong gravity towards `Proc`ess :cyclone: but that should not be strange for π-calculus, _λ-expression_ is not the main _actor_ here. Which reminds me that RHOlang can be interesting to Erlang/Elixir programmers who knows how hard is to coordinate the names of processes. With _reflective higher-order_ super powers that comes with the compiler and type checker it will be pleasure to write smart contracts.

> The π-calculus is elegantly simple, it has very few terms and so is a very small language, yet is very expressive. Functional programs can be encoded into the π-calculus, and the encoding emphasises the dialogue nature of computation, drawing connections with game semantics. [Wikipedia][pi-wiki]

## Configure custom colors on the settings page

Only colors for _Constructor_ and _Bind parameter_ have no default values.

![Idea settings page](./docs/settings-page.png)

## Install

From IntelliJ IDEA [plugins repository][rho-idea] searchable inside the editor.

Or download **rholang-idea.jar** from the latest [release][releases] and install through [File > Settings > Plugins][idea-install-from-disk] menu.

## Contribute

Any suggestions, bugs, testing, pull-requests, issues are very welcome.

### Development

- setup [IntelliJ dev-plugin prerequisites][idea-dev-setup]
- open repo in editor
- generate Java parser from `Rho.bnf` through the file context menu [Generate Parser Code][idea-gen-parser]
- generate Java lexer from `Rho.flex` through the file context menu [Run JFlex Generator][idea-gen-lexer]
- create new Plugin Run configuration with default setup
- run/debug in a separate editor

## TODO

- write Formatter
- write Completion Contributor
- write Reference Contributor
- plugin actions
- connect with the compiler (get semantic info)
- ...

## License

[The MIT License (MIT)][license]

[releases]: https://github.com/tgrospic/rholang-idea/releases
[rchain-coop]: https://www.rchain.coop
[rho-github]: https://github.com/rchain/Rholang
[rho-bnf]: https://github.com/rchain/Rholang/blob/28563311d6ea535ee8ddb425a25152dbf801e89b/src/main/bnfc/rholang.cf
[rho-idea]: https://plugins.jetbrains.com/plugin/9833-rholang
[pi-wiki]: https://en.wikipedia.org/wiki/%CE%A0-calculus

[idea-dev-setup]: http://www.jetbrains.org/intellij/sdk/docs/tutorials/custom_language_support/prerequisites.html
[idea-gen-parser]: http://www.jetbrains.org/intellij/sdk/docs/tutorials/custom_language_support/grammar_and_parser.html#generate-a-parser
[idea-gen-lexer]: http://www.jetbrains.org/intellij/sdk/docs/tutorials/custom_language_support/lexer_and_parser_definition.html#generate-a-lexer-class
[idea-install-from-disk]: https://stackoverflow.com/documentation/intellij-idea/8069/how-to-install-plugins/26027/to-install-a-plugin-from-the-disk#t=201707092022357747782

[beta-badge]: https://cdn.rawgit.com/tgrospic/rholang-idea/master/docs/beta.svg
[license]: https://github.com/tgrospic/rholang-idea/blob/master/LICENSE
