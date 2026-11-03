---
stage: prototype
reviewed_at: 2026-11-02
milestone:
  title: Android targetSdk 36 release
  target: 2026-11-30
  done_when: "1.0.1 (vc 4, targetSdk 36) is published to production on Google Play"
---

## Next steps

- [x] Commit compile/target SDK 36 + versionCode 3 (DUE-63, PR #1)
- [x] Bump to 1.0.1 (vc 4) and build release AAB from committed code (DUE-14)
- [ ] Mark Testing on Play internal track (vc 4)
- [x] Production draft created (Nov 2, ~5:17 PM MT): one Play API edit kept live 1.0 (vc 2) completed and added 1.0.1 [4] as a draft; read-only readback on Linear DUE-14 comment 1009d3a0
- [ ] Mark publishes the production draft himself
- [x] Tag `android-v1.0.1-4` on 1679bd6 (commit the AAB was built from)

## Notes

- Play tracks (Nov 2, 2026, ~5:17 PM MT readback): production draft 1.0.1 (vc 4, target 36) plus live 1.0 (vc 2, target 35) completed; beta 1.0 (vc 2); alpha empty; internal 1.0.1 (vc 4).
- Target 36 is required for the next update (Play policy since Aug 31, 2026), not to clear a current violation.
- Seeded by adopt-state on 2026-07-28; updated Nov 2, 2026 for DUE-14.
